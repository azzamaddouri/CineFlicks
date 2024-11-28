import apiSlice from "./api"

export const authApiSlice = apiSlice.injectEndpoints({
    endpoints: builder => ({
        login: builder.mutation({
            query: ({ body }) => ({
                url: '/api/v1/auth/login',
                method: 'POST',
                body,
            }),
        }),

        register: builder.mutation({
            query: ({ body }) => ({
                url: '/api/v1/auth/register',
                method: 'POST',
                body,
            })
        }),

        activateAccount: builder.query({
            query: ({ token }) => ({
                url: `/api/v1/auth/activate-account`,
                method: 'GET',
                params: token
            })
        })
    }),

})

export const { useLoginMutation, useRegisterMutation, useActivateAccountQuery } = authApiSlice