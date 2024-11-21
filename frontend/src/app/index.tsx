import React, { useEffect, useState } from 'react';
import { StyleSheet, View, Text } from 'react-native';
import LottieView from 'lottie-react-native';
import { useRouter } from 'expo-router';

export default function Index() {
  const [animationComplete, setAnimationComplete] = useState(false);
  const router = useRouter();

  useEffect(() => {
    if (animationComplete) {
      router.push('/auth/login');
    }
  }, [animationComplete]);

  return (
    <View style={styles.splashContainer}>
      <LottieView
        autoPlay
        loop={false}
        style={styles.animation}
        source={require('assets/animations/logo.json')}
        onAnimationFinish={() => setAnimationComplete(true)} // Set state when animation completes
      />
       <Text style={styles.appName}>CINEFLICKS</Text>
    </View>
  );
}

const styles = StyleSheet.create({
    splashContainer: {
      flex: 1,
      backgroundColor: 'black', // Matches static splash background
      justifyContent: 'center',
      alignItems: 'center',
    },
    animation: {
      width: 200,
      height: 200,
    },
    appName: {
      marginTop: 20, // Space between animation and text
      fontSize: 32, // Big text
      fontWeight: 'bold',
      color: 'white', // White color for the text
      fontFamily: 'SpaceMono', // Use the loaded font
      textAlign: 'center',
    },
});