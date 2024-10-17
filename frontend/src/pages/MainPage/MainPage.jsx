import React, { useState } from "react";
import styles from "./index.module.css";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import DataTable from "../../components/DataTable/DataTable";

const tables = [
  {
    tableName: "Изделия",
    tableApi: "http://localhost:8080/api/v1/products",
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
    tableName: "Категории",
    tableApi: "http://localhost:8080/api/v1/categories",
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
    tableName: "Материалы",
    tableApi: "http://localhost:8080/api/v1/materials",
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
    tableName: "Клиенты",
    tableApi: "http://localhost:8080/api/v1/clients",
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
    tableName: "Материалы изделий",
    tableApi: "http://localhost:8080/api/v1/product-materials",
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
    tableName: "Заказы изделий",
    tableApi: "http://localhost:8080/api/v1/product-orders",
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
  const [currentTable, setCurrentTable] = useState(tables[0]);
  function handleTableChange(value) {
    const selectedTable = tables.find((item) => {
      return item.tableApi === value;
    });

    if (selectedTable) {
      setCurrentTable(selectedTable);
    }
  }

  return (
    <div
      style={{
        padding: "100px",
        width: "100%",
        maxWidth: "60%",
        margin: "0 auto",
        display: "flex",
        flexDirection: "column",
        alignContent: "center",
      }}
    >
      <FormControl>
        <InputLabel id="demo-simple-select-label">Таблица</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={currentTable.tableApi}
          label="Age"
          onChange={(event) => handleTableChange(event.target.value)}
        >
          {tables.map((item, index) => (
            <MenuItem value={item.tableApi} key={index}>{item.tableName}</MenuItem>
          ))}
        </Select>
      </FormControl>
      <DataTable
        style={{ marginTop: "40px" }}
        table={currentTable}
      />
    </div>
  );
}

export default MainPage;