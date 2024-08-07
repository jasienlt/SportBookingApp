export const dayOfWeek = [
  'MONDAY',
  'TUESDAY',
  'WEDNESDAY',
  'THURSDAY',
  'FRIDAY',
  'SATURDAY',
  'SUNDAY',
  'All Days',
];
export const dayOfWeekShort = [
  'Mon',
  'Tue',
  'Wed',
  'Thurs',
  'Fri',
  'Sat',
  'Sun',
  'All Days',
];
export function generateDateCheckbox(date) {
  return `<div class="form-check form-check-inline checkbox">
            <input class="form-check-input bookingDayCb" type="checkbox" name="date" id="day-${date}" value="${date + 1}">
            <label class="form-check-label" for="day-${date}">
              ${dayOfWeekShort[date]}
            </label>
          </div>`;
}
