import { StyleSheet, Text, View, TouchableOpacity } from "react-native";
import React from "react";
import { Formik } from "formik";
import { useRouter, Stack } from "expo-router";
import { Button } from "@/components/auth/Button";
import { Input } from "@/components/auth/Input";
import { getErrorMessage } from "@/utils/errorHandler";
import { useRegisterMutation } from "@/services/auth.service";
import { registerSchema } from "@/utils/validation";
import { COLORS } from "@/constants/tokens";

const RegisterScreen = () => {
  const router = useRouter();

  const [register, { isLoading, error }] = useRegisterMutation();

  const handleRegister = async (values: any) => {
    try {
      await register({ body: values }).unwrap();
      router.push("/activate_account");
    } catch (err) {
      console.error("Register error:", err);
    }
  };

  return (
    <>
      <Stack.Screen options={{ headerShown: false }} />
      <View style={styles.container}>
        <Text style={styles.title}>Create an Account</Text>
        <Formik
          initialValues={{
            firstname: "",
            lastname: "",
            email: "",
            password: "",
          }}
          onSubmit={(values) => {
            handleRegister(values);
          }}
          validationSchema={registerSchema}
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
              <Input
                placeholder="Firstname"
                placeholderTextColor="#888"
                onChangeText={handleChange("firstname")}
                onBlur={handleBlur("firstname")}
                value={values.firstname}
                error={errors.firstname}
                touched={touched.firstname}
              />
              <Input
                placeholder="Lastname"
                placeholderTextColor="#888"
                onChangeText={handleChange("lastname")}
                onBlur={handleBlur("lastname")}
                value={values.lastname}
                error={errors.lastname}
                touched={touched.lastname}
              />
              <Input
                placeholder="Email"
                placeholderTextColor="#888"
                keyboardType="email-address"
                onChangeText={handleChange("email")}
                onBlur={handleBlur("email")}
                value={values.email}
                error={errors.email}
                touched={touched.email}
              />

              <Input
                placeholder="Password"
                placeholderTextColor="#888"
                secureTextEntry
                onChangeText={handleChange("password")}
                onBlur={handleBlur("password")}
                value={values.password}
                error={errors.password}
                touched={touched.password}
              />

              {error && (
                <Text style={styles.errorText}>{getErrorMessage(error)}</Text>
              )}

              <Button
                isLoading={isLoading}
                onPress={() => {
                  handleSubmit();
                }}
                text="Register"
              />

              <View style={styles.loginRedirect}>
                <Text style={styles.redirectText}>
                  Already have an account?{" "}
                </Text>
                <TouchableOpacity onPress={() => router.push("/login")}>
                  <Text style={styles.redirectLink}>Login here</Text>
                </TouchableOpacity>
              </View>
            </View>
          )}
        </Formik>
      </View>
    </>
  );
};

export default RegisterScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 16,
    backgroundColor: COLORS.background,
  },
  title: {
    fontSize: 24,
    fontWeight: "bold",
    color: COLORS.text,
    marginBottom: 16,
  },
  form: {
    width: "100%",
  },

  errorText: {
    color: COLORS.primary,
    fontSize: 14,
    marginBottom: 8,
  },

  loginRedirect: {
    flexDirection: "row",
    justifyContent: "center",
    marginTop: 24,
  },
  redirectText: {
    color: "#ccc",
    fontSize: 16,
  },
  redirectLink: {
    color: COLORS.primary,
    fontSize: 16,
    fontWeight: "bold",
  },
});
