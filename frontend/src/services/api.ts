import { RootState } from '@/states/store'
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'




const apiSlice = createApi({
  reducerPath: 'api',
  baseQuery: fetchBaseQuery({
    baseUrl: process.env.EXPO_PUBLIC_BASE_URL,
    prepareHeaders: (headers, { getState }) => {
      const token = (getState() as RootState).auth.token
      if (token) headers.set('authorization', `Bearer ${token}`)
      return headers
    },
  }),
  endpoints: builder => ({}),
})

export default apiSlice