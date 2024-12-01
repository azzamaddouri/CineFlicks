import { useGetUserInfoQuery } from "@/services/user.service";
import useIsAuthenticated from "@/hooks/useIsAuthenticated";
import useExtractEmailFromToken from "@/hooks/useExtractEmailFromToken";
import { useAppDispatch } from "@/hooks/useRedux";
import { userLogout } from "@/states/reducers/authSlice";

export default function useUserInfo() {
  const dispatch = useAppDispatch();
  const isAuthenticated = useIsAuthenticated();
  const email = useExtractEmailFromToken();

  const { data, isLoading, isError } = useGetUserInfoQuery(
    { email },
    { skip: !isAuthenticated || !email }
  );

  if (isError && isAuthenticated) {
    dispatch(userLogout());
  }

  return {
    userInfo: data?.data,
    isAuthenticated,
    isLoading,
  };
}
