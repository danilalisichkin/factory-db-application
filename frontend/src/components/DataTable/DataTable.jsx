import * as React from "react";
import Box from "@mui/material/Box";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import TableSortLabel from "@mui/material/TableSortLabel";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import Checkbox from "@mui/material/Checkbox";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import Tooltip from "@mui/material/Tooltip";
import FormControlLabel from "@mui/material/FormControlLabel";
import Switch from "@mui/material/Switch";
import DeleteIcon from "@mui/icons-material/Delete";
import FilterListIcon from "@mui/icons-material/FilterList";
import { visuallyHidden } from "@mui/utils";
import axios from "axios";
import styled from "@emotion/styled";
import PropTypes from "prop-types";

import ModalWindow from "../ModalWindow/ModalWindow";
import ModalWindowWithForm from "../ModalWindow/ModalWindowWithForm";
import { Button, Grid2, TextField } from "@mui/material";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

function RecordCreatingForm({ fieldsForCreate, onRecordAdded }) {
  const [formData, setFormData] = React.useState({});

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    onRecordAdded(formData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid2
        container
        spacing={2}
        sx={{
          marginTop: "10px",
          marginBottom: "10px",
          flexWrap: "nowrap",
        }}
      >
        {fieldsForCreate.map((field) => (
          <Grid2 item xs={12} sm={field.required ? 6 : 12} key={field.name}>
            <TextField
              label={field.name.charAt(0).toUpperCase() + field.name.slice(1)}
              name={field.name}
              required={field.required}
              fullWidth={true}
              value={formData[field.name] || ""}
              onChange={handleChange}
              variant="outlined"
            />
          </Grid2>
        ))}
        <Button type="submit" variant="contained" color="primary">
          Add
        </Button>
      </Grid2>
    </form>
  );
}

RecordCreatingForm.propTypes = {
  fieldsForCreate: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      required: PropTypes.bool,
    })
  ).isRequired,
  onRecordAdded: PropTypes.func.isRequired,
};

function EnhancedTableHead(props) {
  const {
    order,
    orderBy,
    onRequestSort,
    onSelectAllClick,
    numSelected,
    rowCount,
    columns,
  } = props;

  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <StyledTableRow>
        <StyledTableCell padding="checkbox">
          <Checkbox
            color="primary"
            indeterminate={numSelected > 0 && numSelected < rowCount}
            checked={rowCount > 0 && numSelected === rowCount}
            onChange={onSelectAllClick}
          />
        </StyledTableCell>
        {columns.map((column) => (
          <StyledTableCell
            key={column.id}
            align={column.numeric ? "right" : "left"}
            sortDirection={orderBy === column.id ? order : false}
          >
            <TableSortLabel
              active={orderBy === column.id}
              direction={orderBy === column.id ? order : "asc"}
              onClick={createSortHandler(column.id)}
            >
              {column.label}
              {orderBy === column.id ? (
                <Box component="span" sx={visuallyHidden}>
                  {order === "desc" ? "sorted descending" : "sorted ascending"}
                </Box>
              ) : null}
            </TableSortLabel>
          </StyledTableCell>
        ))}
      </StyledTableRow>
    </TableHead>
  );
}

EnhancedTableHead.propTypes = {
  order: PropTypes.string.isRequired,
  orderBy: PropTypes.string.isRequired,
  onRequestSort: PropTypes.func.isRequired,
  onSelectAllClick: PropTypes.func.isRequired,
  numSelected: PropTypes.number.isRequired,
  rowCount: PropTypes.number.isRequired,
  columns: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      label: PropTypes.string.isRequired,
      numeric: PropTypes.bool.isRequired,
    })
  ).isRequired,
};

function EnhancedTableToolbar(props) {
  const { numSelected, onDeleteClick } = props;
  return (
    <Toolbar>
      {numSelected > 0 ? (
        <Typography variant="subtitle1" component="div">
          {numSelected} selected
        </Typography>
      ) : (
        <Typography variant="h6" component="div">
          {props.tableTitle}
        </Typography>
      )}
      {numSelected > 0 ? (
        <Tooltip title="Delete">
          <IconButton onClick={onDeleteClick}>
            <DeleteIcon />
          </IconButton>
        </Tooltip>
      ) : (
        <Tooltip title="Filter list">
          <IconButton>
            <FilterListIcon />
          </IconButton>
        </Tooltip>
      )}
    </Toolbar>
  );
}

function DataTable(props) {
  const table = props.table;
  const {
    tableIdField: idField,
    tableName: tableTitle,
    fieldsForCreate,
    tableApi: apiUrl,
  } = table;
  const apiRoot = props.apiRoot;

  const modalRef = React.useRef();
  const modalFormRef = React.useRef();
  const [rows, setRows] = React.useState([]);
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState(idField);
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [columns, setColumns] = React.useState([]);

  const openModalWithContent = (
    windowTitle,
    mainMessage,
    additionalMessage,
    closeButtonText
  ) => {
    if (modalRef.current) {
      modalRef.current.openModal(
        windowTitle,
        mainMessage,
        additionalMessage,
        closeButtonText
      );
    }
  };

  const openModalWithFormWithContent = (
    newTitle,
    newFormData,
    newFormFields,
    newCloseButtonText,
    newSubmitButtonText,
    newOnSubmit
  ) => {
    if (modalFormRef.current) {
      modalFormRef.current.openModal(
        newTitle,
        newFormData,
        newFormFields,
        newCloseButtonText,
        newSubmitButtonText,
        newOnSubmit
      );
    }
  };

  React.useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`${apiRoot}/${apiUrl}/all`);
        const data = response.data;

        setRows(data);
        setOrder("asc");
        setOrderBy(table.tableIdField);
        setSelected([]);
        setPage(0);
        setDense(false);
        setRowsPerPage(5);
        setColumns([]);

        if (data.length > 0) {
          setColumns(
            Object.keys(data[0]).map((key) => ({
              id: key,
              label: key.charAt(0).toUpperCase() + key.slice(1),
              numeric: typeof data[0][key] === "number",
            }))
          );
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [table]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === "asc";
    const sortedRows = [...rows].sort((a, b) => {
      if (typeof a[property] === "string" && typeof b[property] === "string") {
        return isAsc
          ? a[property].localeCompare(b[property])
          : b[property].localeCompare(a[property]);
      } else {
        return isAsc ? a[property] - b[property] : b[property] - a[property];
      }
    });
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(property);
    setRows(sortedRows);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelected = rows.map((n) => {
        if (Array.isArray(idField)) {
          return idField.reduce(
            (acc, field) => ({ ...acc, [field]: n[field] }),
            {}
          );
        } else {
          return { [idField]: n[idField] };
        }
      });
      setSelected(newSelected);
      return;
    }
    setSelected([]);
  };

  const handleSelectClick = (event, record) => {
    if (!record) {
      console.error("Record not found!");
      return;
    }

    let id;
    if (Array.isArray(idField)) {
      id = idField.reduce((acc, field) => {
        if (record[field] === undefined) {
          console.error(`Field ${field} does not exist in record`, record);
          return acc;
        }
        return { ...acc, [field]: record[field] };
      }, {});
    } else {
      if (record[idField] === undefined) {
        console.error(`Field ${idField} does not exist in record`, record);
        return;
      }
      id = { [idField]: record[idField] };
    }

    const selectedIndex = selected.findIndex((selectedItem) => {
      if (Array.isArray(idField)) {
        return idField.every((field) => selectedItem[field] === record[field]);
      } else {
        return selectedItem[idField] === record[idField];
      }
    });

    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, id);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1)
      );
    }

    setSelected(newSelected);
  };

  const handleDeleteClick = async (event) => {
    const deleteAll = async () => {
      try {
        const response = await axios.delete(`${apiRoot}/${apiUrl}/all`);
        console.log("Server response:", response.data);
      } catch (error) {
        handleError(error);
        return false;
      }
      return true;
    };

    const deleteSingle = async (record) => {
      try {
        const url = Array.isArray(idField)
          ? `${apiRoot}/${apiUrl}/${record[idField[0]]}/${record[idField[1]]}`
          : `${apiRoot}/${apiUrl}/${record[idField]}`;
        const response = await axios.delete(url);
        console.log("Server response:", response.data);
      } catch (error) {
        handleError(error);
        return false;
      }
      return true;
    };

    const handleError = (error) => {
      console.error("Error while delete:", error);
      if (error.response) {
        const serverErrorResponse = error.response.data;
        openModalWithContent(
          "Error!",
          serverErrorResponse.cause,
          serverErrorResponse.message,
          "OK"
        );
      }
    };

    let success = true;

    if (selected.length === rows.length) {
      success = await deleteAll();
    } else {
      const deletePromises = selected.map(deleteSingle);
      const results = await Promise.all(deletePromises);
      success = results.every((result) => result);
    }

    if (success) {
      setRows((prevRows) =>
        prevRows.filter((row) => {
          return !selected.some((selectedItem) =>
            Array.isArray(idField)
              ? idField.every((field) => selectedItem[field] === row[field])
              : selectedItem[idField] === row[idField]
          );
        })
      );
    }

    setSelected([]);
  };

  const handleEditRecord = (row) => {
    const editRecord = async (record) => {
      try {
        const response = await axios.put(`${apiRoot}/${apiUrl}`, record);
        console.log("Server response:", response.data);
      } catch (error) {
        handleError(error);
        return false;
      }
      const response = await axios.get(`${apiRoot}/${apiUrl}/all`);
      const newRows = response.data;
      setRows(newRows);
      setSelected([]);
      return true;
    };

    const handleError = (error) => {
      console.error("Error while edit:", error);
      if (error.response) {
        const serverErrorResponse = error.response.data;
        openModalWithContent(
          "Error!",
          serverErrorResponse.cause,
          serverErrorResponse.message,
          "OK"
        );
      }
    };

    openModalWithFormWithContent(
      "Edit record",
      row,
      fieldsForCreate,
      "Close",
      "Save",
      editRecord
    );
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleChangeDense = (event) => {
    setDense(event.target.checked);
  };

  const handleAddRecord = async (data) => {
    const postRecord = async (data) => {
      try {
        const response = await axios.post(`${apiRoot}/${apiUrl}`, data);
        console.log("Server response:", response.data);
        return true;
      } catch (error) {
        handleError(error);
        return false;
      }
    };

    const handleError = (error) => {
      console.error("Error while adding:", error);
      if (error.response) {
        const serverErrorResponse = error.response.data;
        openModalWithContent(
          "Error!",
          serverErrorResponse.cause,
          serverErrorResponse.message,
          "OK"
        );
      }
    };

    const success = await postRecord(data);

    if (success) {
      const response = await axios.get(`${apiRoot}/${apiUrl}/all`);
      const newRows = response.data;
      setRows(newRows);
      setSelected([]);
    }
  };

  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - rows.length) : 0;

  const visibleRows = React.useMemo(
    () =>
      [...rows]
        .sort((a, b) =>
          order === "desc" ? b[orderBy] - a[orderBy] : a[orderBy] - b[orderBy]
        )
        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage),
    [order, orderBy, page, rowsPerPage, rows]
  );

  return (
    <Box sx={{ width: "100%" }}>
      <ModalWindowWithForm
        ref={modalFormRef}
        windowTitle="Window title"
        formData={{}}
        closeButtonText="Close"
        submitButtonText="Submit"
      />
      <ModalWindow
        ref={modalRef}
        windowTitle="Window title"
        mainMessage="Main message"
        additionalMessage="Additional message"
        closeButtonText="Close"
      />
      <RecordCreatingForm
        fieldsForCreate={fieldsForCreate}
        onRecordAdded={handleAddRecord}
      />
      <Paper sx={{ width: "100%", mb: 2 }}>
        <EnhancedTableToolbar
          tableTitle={tableTitle}
          numSelected={selected.length}
          onDeleteClick={handleDeleteClick}
        />
        <TableContainer>
          <Table
            sx={{ minWidth: 750 }}
            aria-labelledby="tableTitle"
            size={dense ? "small" : "medium"}
          >
            <EnhancedTableHead
              numSelected={selected.length}
              order={order}
              orderBy={orderBy}
              onSelectAllClick={handleSelectAllClick}
              onRequestSort={handleRequestSort}
              rowCount={rows.length}
              columns={columns}
            />
            <TableBody>
              {visibleRows.map((row, index) => {
                const isItemSelected = Array.isArray(idField)
                  ? selected.some((selectedItem) =>
                      idField.every(
                        (field) => selectedItem[field] === row[field]
                      )
                    )
                  : selected.some(
                      (selectedItem) => selectedItem[idField] === row[idField]
                    );
                const labelId = `enhanced-table-checkbox-${index}`;
                return (
                  <StyledTableRow
                    hover
                    onClick={(event) => handleSelectClick(event, row)}
                    role="checkbox"
                    aria-checked={isItemSelected}
                    tabIndex={-1}
                    key={row[idField]}
                    selected={isItemSelected}
                    sx={{ cursor: "pointer" }}
                  >
                    <StyledTableCell padding="checkbox">
                      <Box display="flex" alignItems="center">
                        <Checkbox
                          color="primary"
                          checked={isItemSelected}
                          inputProps={{ "aria-labelledby": labelId }}
                        />
                        <IconButton
                          onClick={(event) => {
                            event.stopPropagation();
                            handleEditRecord(row);
                          }}
                          aria-label="edit"
                        >
                          <EditIcon />
                        </IconButton>
                      </Box>
                    </StyledTableCell>
                    {columns.map((column) => (
                      <StyledTableCell
                        key={column.id}
                        align={column.numeric ? "right" : "left"}
                      >
                        {row[column.id]}
                      </StyledTableCell>
                    ))}
                  </StyledTableRow>
                );
              })}
              {emptyRows > 0 && (
                <TableRow style={{ height: (dense ? 33 : 53) * emptyRows }}>
                  <StyledTableCell colSpan={columns.length + 1} />
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={rows.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Paper>
      <FormControlLabel
        control={<Switch checked={dense} onChange={handleChangeDense} />}
        label="Dense padding"
      />
    </Box>
  );
}

EnhancedTableToolbar.propTypes = {
  numSelected: PropTypes.number.isRequired,
  onDeleteClick: PropTypes.func.isRequired,
  tableTitle: PropTypes.string.isRequired,
};

export default DataTable;
