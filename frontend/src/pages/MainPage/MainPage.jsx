import React, { useEffect, useState } from "react";
import styles from "./index.module.css";
import {
  Button,
  FormControl,
  FormControlLabel,
  FormLabel,
  InputLabel,
  MenuItem,
  Radio,
  RadioGroup,
  rgbToHex,
  Select,
} from "@mui/material";
import DataTable from "../../components/DataTable/DataTable";

const tables = [
  {
    tableName: "Products",
    tableApi: "api/v1/products",
    tableIdField: "id",
    fieldsForCreate: [
      {
        name: "name",
        required: true,
      },
      {
        name: "price",
        required: true,
      },
      {
        name: "categoryId",
        required: false,
      },
    ],
  },
  {
    tableName: "Categories",
    tableApi: "api/v1/categories",
    tableIdField: "id",
    fieldsForCreate: [
      {
        name: "name",
        required: true,
      },
      {
        name: "parentId",
        required: false,
      },
    ],
  },
  {
    tableName: "Materials",
    tableApi: "api/v1/materials",
    tableIdField: "id",
    fieldsForCreate: [
      {
        name: "name",
        required: true,
      },
      {
        name: "supplierName",
        required: true,
      },
    ],
  },
  {
    tableName: "Clients",
    tableApi: "api/v1/clients",
    tableIdField: "phoneNumber",
    fieldsForCreate: [
      {
        name: "phoneNumber",
        required: true,
      },
      {
        name: "organizationName",
        required: true,
      },
      {
        name: "email",
        required: true,
      },
      {
        name: "legalAddress",
        required: true,
      },
    ],
  },
  {
    tableName: "Product materials",
    tableApi: "api/v1/product-materials",
    tableIdField: ["productSku", "materialSku"],
    fieldsForCreate: [
      {
        name: "productSku",
        required: true,
      },
      {
        name: "materialSku",
        required: true,
      },
    ],
  },
  {
    tableName: "Product orders",
    tableApi: "api/v1/product-orders",
    tableIdField: ["clientPhoneNumber", "productSku"],
    fieldsForCreate: [
      {
        name: "clientPhoneNumber",
        required: true,
      },
      {
        name: "productSku",
        required: true,
      },
      {
        name: "quantity",
        required: true,
      },
    ],
  },
];

function MainPage() {
  const [database, setDatabase] = useState("postgres");
  const [currentTable, setCurrentTable] = useState(tables[0]);
  const [refreshKey, setRefreshKey] = useState(0);

  function handleDataBaseChange(value) {
    let selectedTable = currentTable;
    setDatabase(value);
    setCurrentTable(selectedTable);
  }

  function handleTableChange(value) {
    const selectedTable = tables.find((item) => {
      return item.tableApi === value;
    });

    if (selectedTable) {
      setCurrentTable(selectedTable);
    }
  }

  const useConvertor = async () => {
    await fetch(`http://localhost:8080/converter/convert`);
    setRefreshKey((prevKey) => prevKey + 1);
  };

  useEffect(() => {
    setCurrentTable(tables[0]);
  }, [database]);

  return (
    <div className={styles.pageContainer}>
        <Button
          variant="contained"
          color="primary"
          onClick={useConvertor}
          style={{ marginTop: "20px",  marginBottom: "20px", width: "fit-content"}}
        >
          Convert PostgreSQL to MongoDB
        </Button>
      <FormControl>
        <FormLabel id="demo-controlled-radio-buttons-group">Database</FormLabel>
        <RadioGroup
          aria-labelledby="demo-controlled-radio-buttons-group"
          name="controlled-radio-buttons-group"
          value={database}
          onChange={(event) => handleDataBaseChange(event.target.value)}
        >
          <FormControlLabel
            value="postgres"
            control={<Radio />}
            label="Postgres"
          />
          <FormControlLabel value="mongo" control={<Radio />} label="Mongo" />
        </RadioGroup>
      </FormControl>
      <FormControl>
        <InputLabel id="demo-simple-select-label">Table</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={currentTable.tableApi}
          label="Age"
          onChange={(event) => handleTableChange(event.target.value)}
        >
          {tables.map((item, index) => (
            <MenuItem value={item.tableApi} key={index}>
              {item.tableName}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <DataTable
        key={`${database}-${currentTable.tableApi}-${refreshKey}`}
        style={{ marginTop: "40px" }}
        table={currentTable}
        apiRoot={`http://localhost:8080/${database}`}
      />
    </div>
  );
}

export default MainPage;
