package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {

    operator fun compareTo(other: MyDate): Int {
        if(year != other.year) return year.compareTo(other.year)
        if(month != other.month) return month.compareTo(other.month)
        if(dayOfMonth != other.dayOfMonth) return dayOfMonth.compareTo(other.dayOfMonth)
        return 0
    }

    operator fun plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval, 1)
    operator fun plus(interval: RepeatedTimeInterval): MyDate = this.addTimeIntervals(interval.timeInterval, interval.times)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): RepeatedTimeInterval {
        return RepeatedTimeInterval(this, i)
    }
}
data class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {

            var current = start

            override fun hasNext(): Boolean = current <= endInclusive

            override fun next(): MyDate {
                val previous = current;
                current = current.nextDay()
                return previous
            }
        }
    }

    operator fun contains(date: MyDate): Boolean = start <= date && date <= endInclusive

}

