import { StyleSheet, Text, View, TouchableOpacity } from "react-native"
import { Formik } from "formik"
import { useRouter } from "expo-router"
import { useAppDispatch } from "@/hooks/useRedux"
import { Input } from "@/components/auth/input"
import { Button } from "@/components/auth/button"
import { useLoginMutation } from "@/services/auth.service"
import { getErrorMessage } from "@/utils/errorHandler"
import { userLogin } from "@/states/reducers/authSlice"
import { loginSchema } from "@/utils/validation"

export default function LoginScreen() {
  const dispatch = useAppDispatch()
  const router = useRouter()

  const [login, {isLoading, error}] = useLoginMutation()

  const handleLogin = async (values: any) => {
    try {
      const payload = await login({ body: values }).unwrap()
      dispatch(userLogin(payload?.data?.token))
      router.push("/(tabs)")
    } catch (err) {
      console.error("Login error:", err)
    }
  }

  return (
    <View style={styles.container}>
      <Text style={styles.welcomeText}>Welcome to Cineflicks</Text>
      <Text style={styles.subText}>
        Sign in to continue enjoying your favorite movies!
      </Text>

      <Formik
        initialValues={{ email: "", password: "" }}
        onSubmit={(values) => {
          handleLogin(values)
        }}
        validationSchema={loginSchema}
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
              placeholder="Email"
              placeholderTextColor="#888"
              onChangeText={handleChange("email")}
              onBlur={handleBlur("email")}
              value={values.email}
              keyboardType="email-address"
              autoCapitalize="none"
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
                handleSubmit()
              }}
              text="Login"
            />
          </View>
        )}
      </Formik>

      <View style={styles.registerContainer}>
        <Text style={styles.registerText}>Don't have an account? </Text>
        <TouchableOpacity onPress={() => router.push("/(auth)/register")}>
          <Text style={styles.registerLink}>Register now</Text>
        </TouchableOpacity>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    padding: 16,
    backgroundColor: "black",
  },
  welcomeText: {
    fontSize: 24,
    fontWeight: "bold",
    color: "white",
    marginBottom: 8,
  },
  subText: {
    fontSize: 16,
    color: "#ccc",
    textAlign: "center",
    marginBottom: 32,
  },
  form: {
    width: "100%",
  },

  errorText: {
    color: "red",
    fontSize: 14,
    marginBottom: 8,
  },
  registerContainer: {
    flexDirection: "row",
    marginTop: 24,
  },
  registerText: {
    color: "#ccc",
    fontSize: 16,
  },
  registerLink: {
    color: "red",
    fontSize: 16,
    fontWeight: "bold",
  },
})
