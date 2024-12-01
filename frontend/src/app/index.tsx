import React, { useEffect, useState } from "react";
import { useRouter } from "expo-router";
import AnimatedSplashScreen from "@/app/animated_splash";
import useUserInfo from "@/hooks/useUserInfo";

export default function App() {
  const [animationComplete, setAnimationComplete] = useState(false);
  const router = useRouter();
  const { isAuthenticated, isLoading, userInfo } = useUserInfo();

  useEffect(() => {
    if (animationComplete && !isLoading) {
      if (!isAuthenticated || !userInfo) {
        router.replace("/login");
      } else {
        router.replace("/(tabs)");
      }
    }
  }, [animationComplete, isLoading, isAuthenticated, userInfo, router]);

  return (
    <AnimatedSplashScreen
      onAnimationFinish={() => setAnimationComplete(true)}
    ></AnimatedSplashScreen>
  );
}
