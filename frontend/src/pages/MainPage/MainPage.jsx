import React, { useState } from "react";
import styles from "./index.module.css";
import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import DataTable from "../../components/DataTable/DataTable";

const tables = [
  {
    tableName: "Изделия",
    tableApiName: "products",
    tableIdField: "id"
  },
  {
    tableName: "Категории",
    tableApiName: "categories",
    tableIdField: "id"
  },
  {
    tableName: "Материалы",
    tableApiName: "materials",
    tableIdField: "id"
  },
  {
    tableName: "Клиенты",
    tableApiName: "clients",
    tableIdField: "phoneNumber"
  },
  {
    tableName: "Материалы изделий",
    tableApiName: "product-materials",
    tableIdField: ["productSku", "materialSku"]
  },
  {
    tableName: "Заказы изделий",
    tableApiName: "product-orders",
    tableIdField: ["clientPhoneNumber", "productSku"]
  }
]

function MainPage() {
  const [bdTable, setBdTable] = useState(tables[0]);
  function handleTableChange(value) {
    const selectedTable = tables.find((item) => {
      return item.tableApiName === value;
    });
  
    if (selectedTable) {
      setBdTable(selectedTable);
    }
  }

  return (
    <div style={{padding: "100px", width: "100%", maxWidth: "60%", margin: "0 auto" , display: "flex", flexDirection: "column", alignContent: "center"}}>
      <FormControl>
        <InputLabel id="demo-simple-select-label">Таблица</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={bdTable.tableApiName}
          label="Age"
          onChange={(event) => handleTableChange(event.target.value)}
        >
          {tables.map((item, index) => (
            <MenuItem value={item.tableApiName}>{item.tableName}</MenuItem>
          ))}
        </Select>
      </FormControl>
      <DataTable style={{ marginTop: '40px' }} apiUrl={"http://localhost:8080/api/v1/" + bdTable.tableApiName} tableTitle={bdTable.tableName} idField={bdTable.tableIdField}/>
    </div>
  );
}

export default MainPage;
