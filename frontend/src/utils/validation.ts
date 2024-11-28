import * as Yup from 'yup'


export const loginSchema = Yup.object().shape({
    email: Yup.string().required('Email is required').email("Invalid Email"),
    password: Yup.string().required('Password is required'),
})


export const registerSchema = Yup.object().shape({
    firstname: Yup.string().required('firstname is required'),
    lastname: Yup.string().required('lastname is required'),
    email: Yup.string().required('Email is required').email("Invalid Email"),
    password: Yup.string().required('Password is required'),
})