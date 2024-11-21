import React, { useRef } from 'react';
import { View, StyleSheet } from 'react-native';
import LottieView from 'lottie-react-native';

export default function AnimatedSplashScreen( 
    {
        onAnimationFinish = () =>{}}
        : {onAnimationFinish?: () => void}) {
  const animationRef = useRef(null);

  return (
    <View style={styles.container}>
      <LottieView
        ref={animationRef}
        autoPlay
        loop={false}
        style={styles.animation}
        source={require('@assets/animations/logo.json')}
        onAnimationFinish={onAnimationFinish} // Trigger navigation on finish
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'black', // Matches static splash background
    justifyContent: 'center',
    alignItems: 'center',
  },
  animation: {
    width: 200,
    height: 200,
  },
});
