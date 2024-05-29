/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        'background-main': 'rgb(1, 70, 77)',
        'main-color': 'rgba(0,197,146)',
        'main-color-hover': 'rgba(10,207,156)',
        'background-task': 'rgba(39,39,39)',
      },
    },
  },
  plugins: [],
};