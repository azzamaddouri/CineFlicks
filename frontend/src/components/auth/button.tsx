import { COLORS } from "@/constants/tokens"
import React from "react"
import {TouchableOpacity,Text,StyleSheet,ActivityIndicator,TouchableOpacityProps} from "react-native"


export type ThemedTouchableOpacityProps = TouchableOpacityProps & {
  isLoading: boolean
  text: String
}

export function Button({
  isLoading,
  text,
  ...props
}: ThemedTouchableOpacityProps) {
  return (
    <TouchableOpacity
      style={[styles.button, isLoading && styles.buttonDisabled]}
      disabled={isLoading}
      {...props}
    >
      {isLoading ? (
        <ActivityIndicator size="large" color="white" />
      ) : (
        <Text style={styles.buttonText}>{text}</Text>
      )}
    </TouchableOpacity>
  )
}

const styles = StyleSheet.create({
  button: {
    height: 50,
    backgroundColor: COLORS.primary,
    justifyContent: "center",
    alignItems: "center",
    borderRadius: 8,
    marginTop: 16,
  },
  buttonDisabled: {
    backgroundColor: "#aa0000",
  },
  buttonText: {
    color: COLORS.text,
    fontSize: 18,
    fontWeight: "bold",
  },
})
