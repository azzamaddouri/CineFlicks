import { StyleSheet, Text, TextInput, View, TouchableOpacity } from 'react-native';
import React from 'react';
import { Formik } from 'formik';
import * as Yup from 'yup';
import { useRouter } from 'expo-router';
import { userLogin } from '@/redux/authActions';
import { useDispatch, useSelector } from 'react-redux';
import { useAppSelector } from '@/hooks/useAppSelector';
import { useAppDispatch } from '@/hooks/useAppDispatch';

const validationSchema = Yup.object().shape({
  email: Yup.string().required('Email is required').email().label('Email'),
  password: Yup.string().required('Password is required').min(4).label('Password'),
});

const LoginScreen = () => {
  const router = useRouter();
  const { loading, error } = useAppSelector((state) => state.auth);
  const dispatch = useAppDispatch()
  return (
    <View style={styles.container}>
      <Text style={styles.welcomeText}>Welcome to Cineflicks</Text>
      <Text style={styles.subText}>Sign in to continue enjoying your favorite movies!</Text>

      <Formik
        initialValues={{ email: 'email@gmail.com', password: 'password' }}
        onSubmit={(values) => {
          console.log("Submitting values:", values);
          dispatch(userLogin(values))
          .unwrap()
          .then(() => router.push('/(tabs)'))
          .catch((e) => {
            console.log("error", e);
          });
           }}
        validationSchema={validationSchema}
      >
        {({
          handleChange,
          handleBlur,
          handleSubmit,
          values,
          errors,
          touched,
        }) => (
          <View style={styles.form}>
            <TextInput
              style={styles.input}
              placeholder="Email"
              placeholderTextColor="#888"
              onChangeText={handleChange('email')}
              onBlur={handleBlur('email')}
              value={values.email}
              keyboardType="email-address"
            />
            {errors.email && touched.email && (
              <Text style={styles.errorText}>{errors.email}</Text>
            )}

            <TextInput
              style={styles.input}
              placeholder="Password"
              placeholderTextColor="#888"
              secureTextEntry
              onChangeText={handleChange('password')}
              onBlur={handleBlur('password')}
              value={values.password}
            />
            {errors.password && touched.password && (
              <Text style={styles.errorText}>{errors.password}</Text>
            )}

            <TouchableOpacity style={styles.button} onPress={()=>{handleSubmit()}}>
              <Text style={styles.buttonText}>Login</Text>
            </TouchableOpacity>
          </View>
        )}
      </Formik>

      <View style={styles.registerContainer}>
        <Text style={styles.registerText}>
          Don't have an account?{' '}
        </Text>
        <TouchableOpacity onPress={() => router.push('/auth/register')}>
          <Text style={styles.registerLink}>Register now</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

export default LoginScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
    backgroundColor: 'black',
  },
  welcomeText: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 8,
  },
  subText: {
    fontSize: 16,
    color: '#ccc',
    textAlign: 'center',
    marginBottom: 32,
  },
  form: {
    width: '100%',
  },
  input: {
    height: 50,
    borderColor: '#444',
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 16,
    marginBottom: 16,
    backgroundColor: '#222',
    color: 'white',
  },
  errorText: {
    color: 'red',
    fontSize: 14,
    marginBottom: 8,
  },
  button: {
    height: 50,
    backgroundColor: 'red',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 8,
    marginTop: 16,
  },
  buttonText: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
  },
  registerContainer: {
    flexDirection: 'row',
    marginTop: 24,
  },
  registerText: {
    color: '#ccc',
    fontSize: 16,
  },
  registerLink: {
    color: 'red',
    fontSize: 16,
    fontWeight: 'bold',
  },
});
