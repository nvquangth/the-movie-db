package com.bt.common.date

import com.bt.common.base.BaseTest
import org.junit.Assert
import org.junit.Test
import java.util.Calendar
import java.util.Date

class DateUtilTest : BaseTest() {

    companion object {

        private const val timeString_yyyyMMdd_HHmmss = "2019-07-15 08:25:12"
        private const val format_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss"

        private const val timeString_yyyyMMdd = "2019-07-15"
        private const val format_yyyyMMdd = "yyyy-MM-dd"

        private const val timeString_ddMMyyyy = "15-07-2019"
        private const val format_ddMMyyyy = "dd-MM-yyyy"
        private const val format_illegal = "bb"
        private const val emptyString = ""
        private const val blankString = "       "
    }

    private val timeLongMilliseconds = 1563153912L * 1000
    private val timeDate = Date(timeLongMilliseconds)
    private val timeCalendar = Calendar.getInstance().apply { time = timeDate }

    /**
     * string and format correct
     * return success
     */
    @Test
    fun `string to date success`() {
        Assert.assertEquals(
            timeDate,
            timeString_yyyyMMdd_HHmmss.toDate(format_yyyyMMdd_HHmmss)
        )
    }

    /**
     * string is empty
     * return null
     */
    @Test
    fun `string(empty) to date`() {
        Assert.assertEquals(
            null,
            emptyString.toDate(format_yyyyMMdd)
        )
    }

    /**
     * string is blank
     * return null
     */
    @Test
    fun `string(blank) to date`() {
        Assert.assertEquals(
            null,
            blankString.toDate(format_yyyyMMdd)
        )
    }

    /**
     * format is empty
     * return null
     */
    @Test
    fun `string to date uses empty format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.toDate(emptyString)
        )
    }

    /**
     * format is blank
     * return null
     */
    @Test
    fun `string to date uses blank format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.toDate(blankString)
        )
    }

    /**
     * format is not correct
     * return null
     */
    @Test
    fun `string to date uses illegal format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd_HHmmss.toDate(format_illegal)
        )
    }

    /**
     * string and format correct
     * return success
     */
    @Test
    fun `string to time long success`() {
        Assert.assertEquals(
            timeLongMilliseconds,
            timeString_yyyyMMdd_HHmmss.toTimeLong(format_yyyyMMdd_HHmmss)
        )
    }

    /**
     * string is empty
     * return null
     */
    @Test
    fun `string(empty) to time long`() {
        Assert.assertEquals(
            null,
            emptyString.toTimeLong(format_yyyyMMdd)
        )
    }

    /**
     * string is blank
     * return null
     */
    @Test
    fun `string(blank) to time long`() {
        Assert.assertEquals(
            null,
            blankString.toTimeLong(format_yyyyMMdd)
        )
    }

    /**
     * format is empty
     * return null
     */
    @Test
    fun `string to time long uses empty format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.toTimeLong(emptyString)
        )
    }

    /**
     * format is blank
     * return null
     */
    @Test
    fun `string to time long uses blank format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.toTimeLong(blankString)
        )
    }

    /**
     * format is not correct
     * return null
     */
    @Test
    fun `string to time long use illegal format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd_HHmmss.toTimeLong(format_illegal)
        )
    }

    /**
     * format is empty
     * return null
     */
    @Test
    fun `long to time string uses empty format`() {
        Assert.assertEquals(
            null,
            timeLongMilliseconds.toTimeString(emptyString)
        )
    }

    /**
     * format is blank
     * return null
     */
    @Test
    fun `long to time string uses blank format`() {
        Assert.assertEquals(
            null,
            timeLongMilliseconds.toTimeString(blankString)
        )
    }

    /**
     * format correct
     * return success
     */
    @Test
    fun `long to time string uses full format`() {
        Assert.assertEquals(
            timeString_yyyyMMdd_HHmmss,
            timeLongMilliseconds.toTimeString(format_yyyyMMdd_HHmmss)
        )
    }

    /**
     * format correct
     * return success
     */
    @Test
    fun `long to time string uses sort format`() {
        Assert.assertEquals(
            timeString_yyyyMMdd,
            timeLongMilliseconds.toTimeString(format_yyyyMMdd)
        )
    }

    /**
     * format is not java date time format
     * return null
     */
    @Test
    fun `long to time string uses illegal format`() {
        Assert.assertEquals(
            null,
            timeLongMilliseconds.toTimeString(format_illegal)
        )
    }

    /**
     * string is empty
     * return null
     */
    @Test
    fun `string(empty) change time format`() {
        Assert.assertEquals(
            null,
            emptyString.changeTimeFormat(format_yyyyMMdd, format_ddMMyyyy)
        )
    }

    /**
     * string is blank
     * return null
     */
    @Test
    fun `string(blank) change time format`() {
        Assert.assertEquals(
            null,
            blankString.changeTimeFormat(format_yyyyMMdd, format_ddMMyyyy)
        )
    }

    /**
     * oldFormat is empty
     * return null
     */
    @Test
    fun `string change time format uses old empty format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.changeTimeFormat(emptyString, format_ddMMyyyy)
        )
    }

    /**
     * oldFormat is blank
     * return null
     */
    @Test
    fun `string change time format uses old blank format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.changeTimeFormat(blankString, format_ddMMyyyy)
        )
    }

    /**
     * oldFormat is illegal
     * return null
     */
    @Test
    fun `string change time format uses olf illegal format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.changeTimeFormat(format_illegal, format_yyyyMMdd)
        )
    }

    /**
     * newFormat is empty
     * return null
     */
    @Test
    fun `strng change time format uses new empty format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.changeTimeFormat(format_yyyyMMdd, emptyString)
        )
    }

    /**
     * newFormat is blank
     * return null
     */
    @Test
    fun `string change time format uses new blank format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.changeTimeFormat(format_yyyyMMdd, blankString)
        )
    }

    /**
     * newFormat is blank
     * return null
     */
    @Test
    fun `string chamge time format uses new illegal format`() {
        Assert.assertEquals(
            null,
            timeString_yyyyMMdd.changeTimeFormat(format_yyyyMMdd, format_illegal)
        )
    }

    /**
     * string, oldFormat, newFormat correct
     * return success
     */
    @Test
    fun `string change time format success`() {
        Assert.assertEquals(
            timeString_ddMMyyyy,
            timeString_yyyyMMdd.changeTimeFormat(format_yyyyMMdd, format_ddMMyyyy)
        )
    }

    /**
     * format is empty
     * return null
     */
    @Test
    fun `date to time string uses empty format`() {
        Assert.assertEquals(
            null,
            timeDate.toTimeString(emptyString)
        )
    }

    /**
     * format is blank
     * return null
     */
    @Test
    fun `date to time string uses blank format`() {
        Assert.assertEquals(
            null,
            timeDate.toTimeString(blankString)
        )
    }

    /**
     * format is blank
     * return null
     */
    @Test
    fun `date to time string uses illegal format`() {
        Assert.assertEquals(
            null,
            timeDate.toTimeString(format_illegal)
        )
    }

    /**
     * format is correct
     * return success
     */
    @Test
    fun `date to time string success`() {
        Assert.assertEquals(
            timeString_ddMMyyyy,
            timeDate.toTimeString(format_ddMMyyyy)
        )
    }

    @Test
    fun `date to calendar success`() {
        Assert.assertEquals(
            timeCalendar,
            timeDate.toCalendar()
        )
    }
}
