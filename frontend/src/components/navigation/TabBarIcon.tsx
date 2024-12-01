import { FONT_SIZE } from "@/constants/tokens";
import Ionicons from "@expo/vector-icons/Ionicons";
import { type IconProps } from "@expo/vector-icons/build/createIconSet";
import { type ComponentProps } from "react";

export function TabBarIcon({
  style,
  ...props
}: IconProps<ComponentProps<typeof Ionicons>["name"]>) {
  return (
    <Ionicons
      size={FONT_SIZE.lg}
      style={[{ marginBottom: -3 }, style]}
      {...props}
    />
  );
}
