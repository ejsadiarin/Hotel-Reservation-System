# Multiple Types of Rooms
- creating hotel --> all base price of rooms are the same
- edit setPricePerNight (in Room class) - to determine the type of room
  - Standard - base rate
  - Deluxe - 20% more than base rate (base rate * 0.20 + base rate)
  - Executive - 35% more than base rate (base rate * 0.35 + base rate)

# Discount Codes
- getTotalPrice will first calculate for the RAW TOTAL PRICE
  - then calls calculateDiscount
