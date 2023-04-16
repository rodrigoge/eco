import axios from "axios";

const userApi = axios.create({
    baseURL: import.meta.env.VITE_API_URL_USER,
});

export default userApi;