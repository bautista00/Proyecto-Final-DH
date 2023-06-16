import axios from "axios";

const ec2InstancePort = 8080;
const ec2InstanceIP = "3.19.232.248";

const headers = {
  "Content-Type": "application/json",
};

export const axiosInstance = axios.create({
  baseURL: `http://${ec2InstanceIP}:${ec2InstancePort}`,
  headers,
});

axiosInstance.interceptors.request.use(
  (req) => {
    if (!["/login"].includes(req.url)) {
      if (localStorage.getItem("jwt")) {
        req.headers.Authorization = `Bearer ${localStorage.getItem("jwt")}`;
      }
    }
    return req;
  },
  (err) => Promise.reject(err)
);

axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (err) => {
    //TODO: Cambiar estado a 401, debe venir del backend cuando el token se vence
    if (err.response.status === 500 && localStorage.getItem("jwt")) {
      // localStorage.removeItem("jwt");
      // window.location.href = "/Login";
    }
    return Promise.reject(err);
  }
);
