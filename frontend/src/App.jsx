import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from "./pages/Register.jsx";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route
          path="/"
          element={<h1>Welcome to Bank App</h1>}
        />

        <Route
          path="/register"
          element={<Register />}
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;