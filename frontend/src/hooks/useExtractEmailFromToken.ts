import { useAppSelector } from "./useRedux";
import { jwtDecode } from "jwt-decode";

export default function useExtractEmailFromToken() {
    const token = useAppSelector((state) => state.auth.token);

    if (!token) return null;

    try {
        const decoded = jwtDecode<{ sub: string }>(token);
        const email = decoded.sub;

        if (email) {
            return email;
        }

    } catch (err) {
        console.error("Failed to decode token:", err);
        return null;
    }
}

