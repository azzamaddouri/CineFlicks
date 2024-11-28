import React from "react"
import { View, Text, StyleSheet } from "react-native"
import LottieView from "lottie-react-native"

export default function AnimatedSplashScreen({
  onAnimationFinish = () => {},
}: {
  onAnimationFinish: () => void
}) {
  return (
    <View style={styles.splashContainer}>
      <LottieView
        autoPlay
        loop={false}
        style={styles.animation}
        source={require("@assets/animations/logo.json")}
        onAnimationFinish={onAnimationFinish}
      />
      <Text style={styles.appName}>CINEFLICKS</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  splashContainer: {
    flex: 1,
    backgroundColor: "black",
    justifyContent: "center",
    alignItems: "center",
  },
  animation: {
    width: 200,
    height: 200,
  },
  appName: {
    marginTop: 20,
    fontSize: 32,
    fontWeight: "bold",
    color: "white",
    fontFamily: "SpaceMono",
    textAlign: "center",
  },
})
