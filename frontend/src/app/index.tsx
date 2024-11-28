import React, { useEffect, useState } from "react"
import { useRouter } from "expo-router"
import AnimatedSplashScreen from "@/app/animated_splash"

export default function App() {
  const [animationComplete, setAnimationComplete] = useState(false)
  const router = useRouter()

  useEffect(() => {
    if (animationComplete) {
      router.push("/(auth)/login")
    }
  }, [animationComplete])

  return (
    <AnimatedSplashScreen
      onAnimationFinish={() => setAnimationComplete(true)}
    ></AnimatedSplashScreen>
  )
}
