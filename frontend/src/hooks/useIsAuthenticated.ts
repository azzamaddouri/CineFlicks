import { useAppSelector, useAppDispatch } from "./useRedux";
import { jwtDecode } from "jwt-decode";
import { userLogout } from "@/states/reducers/authSlice";

export default function useIsAuthenticated() {
    const token = useAppSelector((state) => state.auth.token);
    const dispatch = useAppDispatch();

    if (!token) return false;

    try {
        const { exp } = jwtDecode<{ exp: number }>(token);
        if (!exp || exp <= Date.now() / 1000) {
            dispatch(userLogout());
            return false;
        }
        return true;
    } catch (err) {
        console.error("Failed to decode token:", err);
        dispatch(userLogout());
        return false;
    }
}
