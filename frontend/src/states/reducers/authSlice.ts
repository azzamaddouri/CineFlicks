import { createSlice } from "@reduxjs/toolkit"
import { RootState } from "@/states/store"

type initialState = {
  token: string
}

const initialState: initialState = {
  token: '',
}

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    userLogin: (state, action) => {
      state.token = action.payload
    },
    userLogout: (state) => {
      state.token = ''
    }
  },
})

export const selectAuth = (state: RootState) => state.auth

export const { userLogin, userLogout } = authSlice.actions


export default authSlice.reducer