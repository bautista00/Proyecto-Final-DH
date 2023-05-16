import { Routes, Route, BrowserRouter } from "react-router-dom";
import Home from "./pages/Home/Home";
import { GlobalStyle } from "./styles/GlobalStyle";
import { GlobalThemeProvider } from "./common/components/GlobalThemeProvider";
import { theme } from "./styles/theme";

function App() {
  return (
    <>
      <GlobalThemeProvider theme={theme}>
        <GlobalStyle />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
          </Routes>
        </BrowserRouter>
      </GlobalThemeProvider>
    </>
  );
}

export default App;
