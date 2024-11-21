import { StyleSheet, Text, TextInput, View, TouchableOpacity } from 'react-native';
import React from 'react';
import { Formik } from 'formik';
import * as Yup from 'yup';
import { useRouter } from 'expo-router';

const validationSchema = Yup.object().shape({
  firstName: Yup.string().required('First name is required').label('First Name'),
  lastName: Yup.string().required('Last name is required').label('Last Name'),
  email: Yup.string().required('Email is required').email('Invalid email format').label('Email'),
  password: Yup.string()
    .required('Password is required')
    .min(6, 'Password must be at least 6 characters')
    .label('Password'),
});

const RegisterScreen = () => {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Create an Account</Text>
      <Formik
        initialValues={{ firstName: '', lastName: '', email: '', password: '' }}
        onSubmit={(values) => console.log('Registration Values:', values)}
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
              placeholder="First Name"
              placeholderTextColor="#888"
              onChangeText={handleChange('firstName')}
              onBlur={handleBlur('firstName')}
              value={values.firstName}
            />
            {errors.firstName && touched.firstName && (
              <Text style={styles.errorText}>{errors.firstName}</Text>
            )}

            <TextInput
              style={styles.input}
              placeholder="Last Name"
              placeholderTextColor="#888"
              onChangeText={handleChange('lastName')}
              onBlur={handleBlur('lastName')}
              value={values.lastName}
            />
            {errors.lastName && touched.lastName && (
              <Text style={styles.errorText}>{errors.lastName}</Text>
            )}

            <TextInput
              style={styles.input}
              placeholder="Email"
              placeholderTextColor="#888"
              keyboardType="email-address"
              onChangeText={handleChange('email')}
              onBlur={handleBlur('email')}
              value={values.email}
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

            <TouchableOpacity
              style={styles.button}
              onPress={() => handleSubmit()}
            >
              <Text style={styles.buttonText}>Register</Text>
            </TouchableOpacity>

            <View style={styles.loginRedirect}>
              <Text style={styles.redirectText}>Already have an account? </Text>
              <TouchableOpacity onPress={() => router.push('/auth/login')}>
                <Text style={styles.redirectLink}>Login here</Text>
              </TouchableOpacity>
            </View>
          </View>
        )}
      </Formik>
    </View>
  );
};

export default RegisterScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
    backgroundColor: 'black',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
    marginBottom: 16,
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
  loginRedirect: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: 24,
  },
  redirectText: {
    color: '#ccc',
    fontSize: 16,
  },
  redirectLink: {
    color: 'red',
    fontSize: 16,
    fontWeight: 'bold',
  },
});
