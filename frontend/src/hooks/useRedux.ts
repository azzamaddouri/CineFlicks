import { useDispatch, useSelector } from 'react-redux'
import { AppDispatch, RootState } from "@/states/store"

export const useAppSelector = useSelector.withTypes<RootState>()
export const useAppDispatch = useDispatch.withTypes<AppDispatch>()
