import { apiClient } from "./ApiClient";

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`
    ,{
        headers: {
            Authorization: token
        }
    }
    )

export const executeJwtAuthenticationService
    = (username, password) => 
        apiClient.post(`/authenticate`,{username,password})

export const executeJwtAuthenticationService2
    = (username, password) => 
        apiClient.post(`/authenticate2`,{username,password})

