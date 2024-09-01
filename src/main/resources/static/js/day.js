document.addEventListener('DOMContentLoaded', function () {
    const yearSelect = document.getElementById('year');
    const monthSelect = document.getElementById('month');
    const daySelect = document.getElementById('day');

    // 연도 옵션 생성
    for (let year = 2024; year >= 1900; year--) {
        const option = document.createElement('option');
        option.value = year;
        option.textContent = year;
        yearSelect.appendChild(option);
    }

    // 월 옵션 생성
    for (let month = 1; month <= 12; month++) {
        const option = document.createElement('option');
        option.value = month;
        option.textContent = month;
        monthSelect.appendChild(option);
    }

    function updateDayOptions() {
        const year = parseInt(yearSelect.value);
        const month = parseInt(monthSelect.value);

        // 윤년 계산
        const isLeapYear = (year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0);

        // 월에 따른 일수 계산
        let maxDays;
        if (month === 2) {
            maxDays = isLeapYear ? 29 : 28;
        } else if ([4, 6, 9, 11].includes(month)) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }

        // 기본 옵션을 제외하고 기존의 일 옵션 제거
        daySelect.options.length = 1; // 첫 번째 옵션 ("일을 선택하세요")를 남겨둠

        // 새로운 일 옵션 생성
        for (let day = 1; day <= maxDays; day++) {
            const option = document.createElement('option');
            option.value = day;
            option.textContent = day;
            daySelect.appendChild(option);
        }
    }

    // 이벤트 리스너 추가
    yearSelect.addEventListener('change', updateDayOptions);
    monthSelect.addEventListener('change', updateDayOptions);

    // 초기화
    updateDayOptions();
});
