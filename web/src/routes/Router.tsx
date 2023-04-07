import { BrowserRouter, Route, Routes } from "react-router-dom";
import RegisterUser from "../pages/RegisterUser";

export default function Router() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/user/register" element={<RegisterUser />} />
            </Routes>
        </BrowserRouter>
    );
}