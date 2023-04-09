import { BrowserRouter, Route, Routes } from "react-router-dom";
import Homepage from "../pages/Homepage";
import RegisterUser from "../pages/RegisterUser";

export default function Router() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Homepage />} />
                <Route path="/user/register" element={<RegisterUser />} />
            </Routes>
        </BrowserRouter>
    );
}