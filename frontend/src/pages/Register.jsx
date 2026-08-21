import { useState } from "react";
import { registerCustomer } from "../services/api";

function Register() {

  const [formData, setFormData] = useState({
    customerFirstName: "",
    customerLastName: "",
    customerAddress: "",
    customerEmail: "",
    customerPhone: "",
    customerDateOfBirth: "",
    customerPassword: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      const data = await registerCustomer(formData);

      console.log("Register response:", data);

      alert("Registration successful!");

    } catch (error) {

      console.error("Registration failed:", error);

      alert("Registration failed!");

    }
  };

  return (
    <div className="register-container">

      <h2>Create Bank Account</h2>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="customerFirstName"
          placeholder="First Name"
          value={formData.customerFirstName}
          onChange={handleChange}
        />

        <input
          type="text"
          name="customerLastName"
          placeholder="Last Name"
          value={formData.customerLastName}
          onChange={handleChange}
        />

        <input
          type="text"
          name="customerAddress"
          placeholder="Address"
          value={formData.customerAddress}
          onChange={handleChange}
        />

        <input
          type="email"
          name="customerEmail"
          placeholder="Email"
          value={formData.customerEmail}
          onChange={handleChange}
        />

        <input
          type="tel"
          name="customerPhone"
          placeholder="Phone Number"
          value={formData.customerPhone}
          onChange={handleChange}
        />

        <input
          type="date"
          name="customerDateOfBirth"
          value={formData.customerDateOfBirth}
          onChange={handleChange}
        />

        <input
          type="password"
          name="customerPassword"
          placeholder="Password"
          value={formData.customerPassword}
          onChange={handleChange}
        />

        <button type="submit">
          Register
        </button>

      </form>

    </div>
  );
}

export default Register;