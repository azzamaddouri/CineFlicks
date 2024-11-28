export const getErrorMessage = (error: any): string => {
    if ("data" in error && error.data.validationErrors) {
      return error.data.validationErrors.join(", ")
    }
  
    if ("data" in error && error.data.error) {
      return error.data.error
    }
  
    return "An unexpected error occurred."
  }
  