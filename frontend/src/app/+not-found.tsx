import { Stack } from "expo-router";
import { Text, View, Image, StyleSheet, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";
import { COLORS } from "@/constants/tokens";

export default function NotFoundPage() {
  const router = useRouter();

  return (
    <>
      <Stack.Screen
        options={{
          title: "404 Not Found!",
          headerBackTitleVisible: false,
        }}
      />
      <View style={styles.container}>
        <Text style={styles.title}>Oops! Page Not Found</Text>
        <Image
          style={styles.image}
          source={require("@assets/images/page-not-found.png")}
          alt="404"
        />
        <Text style={styles.message}>
          The page you're looking for doesn't exist or has been moved.
        </Text>
        <TouchableOpacity
          style={styles.button}
          onPress={() => router.push("/")}
        >
          <Text style={styles.buttonText}>Go Back Home</Text>
        </TouchableOpacity>
      </View>
    </>
  );
}

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
  image: {
    width: "100%",
    height: 250,
    resizeMode: "contain",
    marginBottom: 16,
  },
  message: {
    fontSize: 16,
    color: COLORS.text,
    textAlign: "center",
    marginBottom: 24,
  },
  button: {
    backgroundColor: "red",
    paddingVertical: 14,
    paddingHorizontal: 32,
    borderRadius: 8,
    alignItems: "center",
  },
  buttonText: {
    color: COLORS.text,
    fontWeight: "bold",
    fontSize: 18,
  },
});
