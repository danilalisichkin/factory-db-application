import { ThemeProvider } from '@emotion/react';
import './App.css';
import MainPage from './pages/MainPage/MainPage';
import { createTheme } from '@mui/material';

const theme = createTheme();

function App() {
  return (
    <ThemeProvider theme={theme}>
      <MainPage />
    </ThemeProvider>
  );
}

export default App;
