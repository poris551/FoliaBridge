## Bukkit <-> Folia Scheduler 연동

> 라이브러리 이용시 초기화 작업필요 ( Task.init() )
> 플러그인 이용시 별도 초기화 필요없음


`전역 스케줄러`
> run(A)Sync 

`코루틴 전역 스케줄러` **(멀티코어 미사용)**
> launch(A)Sync

`전역 동기 딜레이 스케줄러` 
> runLater

`Folia용 플레이어, 로케이션 기준 스케줄러`
> runSyncFolia(Player||Location) 

`메세지 전송`
> sendMessage(Player)








TODO : Folia 플레이어, 로케이션 기반 비동기 추가 / 로거 추가, 전체 딜레이 스케줄러 추가
