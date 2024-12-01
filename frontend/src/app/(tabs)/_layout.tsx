import { View, Text, StyleSheet, Image, TouchableOpacity } from "react-native";
import { Tabs } from "expo-router";
import React from "react";
import { TabBarIcon } from "@/components/navigation/TabBarIcon";
import { SafeAreaView } from "react-native-safe-area-context";
import { COLORS, FONT_SIZE } from "@/constants/tokens";
import useUserInfo from "@/hooks/useUserInfo";

export default function TabLayout() {
  const { userInfo } = useUserInfo();

  return (
    <>
      <SafeAreaView>
        <View style={styles.topBar}>
          <View style={styles.textContainer}>
            <Text style={styles.appName}>CineFlicks</Text>
            <Text style={styles.username}>{userInfo?.username}</Text>
          </View>
          <View style={styles.iconContainer}>
            <TouchableOpacity style={styles.icon}>
              <TabBarIcon name="notifications-outline" color="white" />
            </TouchableOpacity>
            <TouchableOpacity>
              <Image
                source={{ uri: "https://via.placeholder.com/40" }}
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
            fontSize: FONT_SIZE.xs,
            color: "white",
            fontWeight: "500",
          },
          tabBarActiveTintColor: COLORS.primary,
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
    backgroundColor: COLORS.background,
    paddingHorizontal: 16,
    paddingVertical: 10,
  },
  textContainer: {
    flexDirection: "column",
  },
  appName: {
    color: COLORS.text,
    fontSize: FONT_SIZE.lg,
    fontWeight: "700",
  },
  username: {
    color: "gray",
    fontSize: FONT_SIZE.sm,
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
