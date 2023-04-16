import axios from "axios";

const pointApi = axios.create({
    baseURL: import.meta.env.VITE_API_URL_POINT,
});

export default pointApi;