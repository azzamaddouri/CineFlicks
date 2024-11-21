import { createSlice } from '@reduxjs/toolkit';
import { userLogin } from './authActions';
import { RootState } from '@/redux/store';

interface AuthState {
  loading: boolean;
  authToken: string | null;
  error: string | null;
  success: boolean;
}

const initialState: AuthState = {
  loading: false,
  authToken: null,
  error: null,
  success: false,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(userLogin.pending, (state) => {
        state.loading = true;
        state.error = null;
        state.success = false;
      })
      .addCase(userLogin.fulfilled, (state, { payload }) => {
        state.loading = false;
        state.authToken = payload?.token || null; // Adjust for token presence
        state.success = true;
      })
      .addCase(userLogin.rejected, (state, { payload }) => {
        state.loading = false;
        state.error = payload as string; // Ensure `payload` is typed
      });
  },
});

export const selectAuth = (state: RootState) => state.auth;
export default authSlice.reducer;
