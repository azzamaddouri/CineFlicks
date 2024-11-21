import { View, Text } from 'react-native'
import React from 'react'
import { Stack } from 'expo-router'
import { defaultStyles } from '@/styles'

export default function ChatRoomsScreenLayout () {
  return (
    <View style={defaultStyles.container}>
        <Stack>
            <Stack.Screen name='index'options={{headerTitle: 'Chat'}} />
        </Stack>
    </View>
  )
}

