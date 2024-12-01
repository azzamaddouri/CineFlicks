import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  ActivityIndicator,
} from "react-native";
import React, { useState, useRef } from "react";
import { useRouter, Stack } from "expo-router";
import { OtpInput, OtpInputRef } from "react-native-otp-entry";
import { useActivateAccountQuery } from "@/services/auth.service";
import { getErrorMessage } from "@/utils/errorHandler";
import { COLORS, FONT_SIZE } from "@/constants/tokens";

const AccountActivation = () => {
  const router = useRouter();

  const otpInputRef = useRef<OtpInputRef | null>(null);

  const [queryToken, setQueryToken] = useState("");
  const [showError, setShowError] = useState(false);
  const { isLoading, isSuccess, isError, error } = useActivateAccountQuery(
    { token: queryToken },
    { skip: !queryToken }
  );

  const onCodeCompleted = (code: string) => {
    setQueryToken(code);
    setShowError(true);
  };

  const resetOtpInput = () => {
    setShowError(false);
    otpInputRef.current?.clear();
  };

  return (
    <>
      <Stack.Screen options={{ headerShown: false }} />
      <View style={styles.container}>
        <Text style={styles.title}>Activate Your Account</Text>
        <Text style={styles.infoText}>
          A verification code has been sent to your email. Please enter it
          below.
        </Text>
        <OtpInput
          ref={otpInputRef}
          numberOfDigits={6}
          focusColor={COLORS.primary}
          focusStickBlinkingDuration={500}
          onFilled={onCodeCompleted}
          type="numeric"
          textInputProps={{
            accessibilityLabel: "One-Time Password",
          }}
          theme={{
            pinCodeTextStyle: styles.pinCodeText,
          }}
        />

        {isLoading && (
          <ActivityIndicator
            size="large"
            color="white"
            style={{ marginVertical: 16 }}
          />
        )}

        {showError && isError && (
          <View style={styles.feedbackContainer}>
            <Text style={[styles.feedbackText, styles.errorText]}>
              Activation failed!
            </Text>
            <Text style={styles.errorText}>{getErrorMessage(error)}</Text>
            <TouchableOpacity style={styles.button} onPress={resetOtpInput}>
              <Text style={styles.buttonText}>Try Again</Text>
            </TouchableOpacity>
          </View>
        )}

        {isSuccess && (
          <View style={styles.feedbackContainer}>
            <Text style={[styles.feedbackText, styles.successText]}>
              Activation Successful!
            </Text>
            <Text style={styles.successText}>
              Your account has been successfully activated.{"\n"}You can now
              proceed to login.
            </Text>
            <TouchableOpacity
              style={styles.button}
              onPress={() => router.push("/login")}
            >
              <Text style={styles.buttonText}>Login</Text>
            </TouchableOpacity>
          </View>
        )}
      </View>
    </>
  );
};

export default AccountActivation;

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
    textAlign: "center",
  },
  infoText: {
    color: COLORS.text,
    fontSize: FONT_SIZE.sm,
    marginBottom: 16,
    textAlign: "center",
    flexWrap: "wrap",
  },
  pinCodeText: {
    color: COLORS.text,
    fontSize: 18,
    textAlign: "center",
  },
  feedbackContainer: {
    marginTop: 24,
    alignItems: "center",
    width: "100%",
  },
  feedbackText: {
    fontSize: FONT_SIZE.sm,
    marginBottom: 8,
    textAlign: "center",
  },
  errorText: {
    color: COLORS.primary,
    fontSize: FONT_SIZE.sm,
    textAlign: "center",
  },
  successText: {
    color: "green",
    fontSize: FONT_SIZE.sm,
    textAlign: "center",
  },
  button: {
    backgroundColor: COLORS.primary,
    paddingVertical: 14,
    paddingHorizontal: 32,
    borderRadius: 8,
    marginTop: 16,
    alignItems: "center",
    width: "100%",
  },
  buttonText: {
    color: COLORS.text,
    fontWeight: "bold",
    fontSize: 18,
    textAlign: "center",
  },
});
