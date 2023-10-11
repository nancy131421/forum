import axios from "axios"

const instance = axios.create({
    baseURL: "http://localhost:8080/"
});
   
instance.interceptors.request.use(
    config => {
        //request拦截器，将token放入header
        let token = sessionStorage.getItem("token"); 
        if (token) config.headers['authorization'] = token;
        return config
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)

export default (config) => {
    return instance(config);
}