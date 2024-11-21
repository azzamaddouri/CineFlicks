import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';

const backendURL = 'http://10.0.2.2:8222';

export const userLogin = createAsyncThunk(
  'auth/login',
  async (
    { email, password }: { email: string; password: string },
    { rejectWithValue }
  ) => {
    try {
      const config = {
        headers: {
          'Content-Type': 'application/json',
        },
      };
      const { data } = await axios.post(
        `${backendURL}/api/v1/auth/login`,
        { email, password },
        config
      );

      if (data.data?.token) {
        return { token: data.data?.token };
      } 
      
    } catch (error: any) {
      if (axios.isAxiosError(error)) {
        // Log the error details from Axios response
        console.error('Axios error:', error.response?.data || error.message);
        // Check if the error response has a message
        return rejectWithValue(error.response?.data?.error || error.message);
      } else {
        // Handle unexpected errors (e.g., network errors)
        console.error('Unexpected error:', error);
        return rejectWithValue(error.message);
      }
    }
  }
);
