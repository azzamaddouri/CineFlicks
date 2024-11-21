const axios = require("axios");

const baseUrl = "http://127.0.0.1:8222";

const login = async (email, password) => {
    try {
        const response = await axios.post(
            `${baseUrl}/api/v1/auth/login`,
            { email, password }          
        );
        return response.data;             
    } catch (error) {
        console.error(error.message); 
        throw error;                      
    }
};


export {login}