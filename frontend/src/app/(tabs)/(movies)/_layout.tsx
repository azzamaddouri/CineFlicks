import { View, Text } from 'react-native'
import React from 'react'
import { Stack } from 'expo-router'
import { defaultStyles } from '@/styles'

const MoviesScreenLayout = () => {
  return (
    <View style={defaultStyles.container}>
        <Stack>
            <Stack.Screen name='index'options={{headerShown:false}} />
        </Stack>
    </View>
  )
}

export default MoviesScreenLayout