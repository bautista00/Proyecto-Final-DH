import { Routes, Route, BrowserRouter } from "react-router-dom";
import Home from "./pages/Home/Home";
import { GlobalStyle } from "./styles/GlobalStyle";
import { GlobalThemeProvider } from "./common/components/GlobalThemeProvider";
import { theme } from "./styles/theme";
import { CreateProduct } from "./pages/CreateProduct";
import { Detail } from "./pages/Detail";

function App() {
  return (
    <>
      <GlobalThemeProvider theme={theme}>
        <GlobalStyle />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/CreateProduct" element={<CreateProduct />} />
            <Route path="/Detail/:id" element={<Detail />} />
          </Routes>
        </BrowserRouter>
      </GlobalThemeProvider>
    </>
  );
}

export default App;
