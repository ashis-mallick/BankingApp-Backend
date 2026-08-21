const API_URL = "http://localhost:8080";

export const registerCustomer = async (formData) => {
  const response = await fetch(`${API_URL}/register`, {
    method: "POST",

    headers: {
      "Content-Type": "application/json",
    },

    body: JSON.stringify(formData),
  });

  if (!response.ok) {
    throw new Error("Registration failed");
  }

  return response.json();
};