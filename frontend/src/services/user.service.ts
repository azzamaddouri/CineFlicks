import apiSlice from "./api";

export const userApiSlice = apiSlice.injectEndpoints({
    endpoints: builder => ({
        getUserInfo: builder.query({
            query: ({ email }) => ({
                url: `/api/v1/user/getUserByEmail/${email}`,
                method: 'GET'
            })
        })
    }),
})


export const {useGetUserInfoQuery} = userApiSlice