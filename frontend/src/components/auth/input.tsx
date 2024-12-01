import { COLORS } from "@/constants/tokens"
import React from "react"
import {
  StyleSheet,
  Text,
  TextInput,
  TextInputProps,
  View,
} from "react-native"

export type ThemedTextInputProps = TextInputProps & {
  error: string | undefined
  touched: boolean | undefined
}

export function Input({ error, touched, ...props }: ThemedTextInputProps) {
  return (
    <View style={styles.inputContainer}>
      <TextInput
        style={styles.input}
        placeholderTextColor="#888"
        autoCapitalize="none"
        {...props}
      />
      {error && touched && <Text style={styles.errorText}>{error}</Text>}
    </View>
  )
}

const styles = StyleSheet.create({
  inputContainer: {
    marginBottom: 16,
  },
  input: {
    height: 50,
    borderColor: "#444",
    borderWidth: 1,
    borderRadius: 8,
    paddingHorizontal: 16,
    backgroundColor: "#222",
    color: "white",
  },
  errorText: {
    color: COLORS.primary,
    fontSize: 14,
    marginTop: 4,
  },
})
