import { configureStore } from '@reduxjs/toolkit'
import authReducer from '@/states/reducers/authSlice'
import apiSlice from '@/services/api'
import { setupListeners } from '@reduxjs/toolkit/query'
import AsyncStorage from '@react-native-async-storage/async-storage'
import { persistReducer, persistStore } from 'redux-persist'


const persistConfig = {
  key: 'root',
  version: 1,
  storage: AsyncStorage,
}


const authPersistedReducer = persistReducer(persistConfig, authReducer)


const store = configureStore({
  reducer: {
    auth: authPersistedReducer,
    [apiSlice.reducerPath]: apiSlice.reducer
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      immutableCheck: false,
      serializableCheck: false,
    }).concat(apiSlice.middleware),
})


export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch


setupListeners(store.dispatch)

export const persistor = persistStore(store)

export default store