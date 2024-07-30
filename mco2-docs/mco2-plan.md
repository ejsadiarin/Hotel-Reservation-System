# Multiple Types of Rooms

- creating hotel --> all base price of rooms are the same
- edit setPricePerNight (in Room class) - to determine the type of room
  - Standard - base rate
  - Deluxe - 20% more than base rate (base rate \* 0.20 + base rate)
  - Executive - 35% more than base rate (base rate \* 0.35 + base rate)

# Discount Codes

- getTotalPrice will first calculate for the RAW TOTAL PRICE
  - then calls calculateDiscount
  - e

# GUI

- One frame
- check the DisplayManager for the Views/GUIs to create
- have pictures for design

  - Panels:
    - Main Menu (Starting)
      - has createHotel, viewHotels, manageHotel, simulate Booking buttons
      - Show hotels created here
    - Hotel Management Menu (Managing Hotels)
      - manage hotel UI with buttons
    - View Guest Info
      - Viewing Guest's reservations
    - Viewing Rooms
      - View availability dates for rooms
      - View current reservations for the specific room
    - Viewing Rooms
      - View availability dates for rooms

  /\*

  - PRIO: todos for last
  - - [x] applying discount to reservation
  - - [x] createTemporaryReservation to show total price
  - - [x] in ViewSpecificReservationFrame, add JLabel for "Discount Code: N/A"
  - - [ ] maybe also the: if already booked reservation, then change date price,
  - the id booked reservation price should not changed
  - - [ ] pretty UI
  - - [ ] documentation
  - - [ ] misc
  - - [ ] demo video
          \*/
