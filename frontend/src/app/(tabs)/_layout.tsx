import {View, Text, StyleSheet, Image, TouchableOpacity } from "react-native";
import { Tabs } from "expo-router";
import React from "react";
import { TabBarIcon } from "@/components/navigation/TabBarIcon";
import { colors, fontSize } from "@/constants/tokens";
import { SafeAreaView } from "react-native-safe-area-context";

export default function TabLayout() {
  const username = "JohnDoe"; // Replace with actual username from your state/store

  return (
    <>

      {/* Top Section */}
      <SafeAreaView>
        <View style={styles.topBar}>
          <View style={styles.textContainer}>
            <Text style={styles.appName}>CineFlicks</Text>
            <Text style={styles.username}>{username}</Text>
          </View>
          <View style={styles.iconContainer}>
            <TouchableOpacity style={styles.icon}>
              <TabBarIcon name="notifications-outline" color="white" />
            </TouchableOpacity>
            <TouchableOpacity>
              <Image
                source={{ uri: "https://via.placeholder.com/40" }} // Replace with user profile picture URL
                style={styles.profilePicture}
              />
            </TouchableOpacity>
          </View>
        </View>
      </SafeAreaView>

      {/* Tabs */}
      <Tabs
        screenOptions={{
          tabBarStyle: {
            backgroundColor: "black",
          },
          tabBarLabelStyle: {
            fontSize: fontSize.xs,
            color: "white",
            fontWeight: "500",
          },
          tabBarActiveTintColor: colors.primary,
          tabBarInactiveTintColor: "gray",
        }}
      >
        <Tabs.Screen
          name="(movies)"
          options={{
            title: "Home",
            tabBarIcon: ({ color, focused }) => (
              <TabBarIcon
                name={focused ? "home" : "home-outline"}
                color={color}
              />
            ),
          }}
        />
        <Tabs.Screen
          name="ticket_booking"
          options={{
            title: "Ticket Booking",
            tabBarIcon: ({ color, focused }) => (
              <TabBarIcon
                name={focused ? "ticket-sharp" : "ticket-outline"}
                color={color}
              />
            ),
          }}
        />
        <Tabs.Screen
          name="chat_rooms"
          options={{
            title: "Chat Rooms",
            tabBarIcon: ({ color, focused }) => (
              <TabBarIcon
                name={focused ? "chatbubbles" : "chatbubbles-outline"}
                color={color}
              />
            ),
          }}
        />
      </Tabs>
    </>
  );
}

const styles = StyleSheet.create({
  topBar: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    backgroundColor: "black",
    paddingHorizontal: 16,
    paddingVertical: 10,
  },
  textContainer: {
    flexDirection: "column",
  },
  appName: {
    color: "white",
    fontSize: fontSize.lg,
    fontWeight: "700",
  },
  username: {
    color: "gray",
    fontSize: fontSize.sm,
    fontWeight: "500",
  },
  iconContainer: {
    flexDirection: "row",
    alignItems: "center",
  },
  icon: {
    marginRight: 16,
  },
  profilePicture: {
    width: 40,
    height: 40,
    borderRadius: 20,
    borderWidth: 1,
    borderColor: "white",
  },
});
